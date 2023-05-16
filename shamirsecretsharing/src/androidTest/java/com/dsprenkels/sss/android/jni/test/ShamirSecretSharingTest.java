package com.dsprenkels.sss.android.jni.test;


import com.dsprenkels.sss.android.jni.ShamirSecretSharing;

import org.junit.Test;
import org.junit.runner.RunWith;
import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import androidx.test.ext.junit.runners.AndroidJUnit4;

@RunWith(AndroidJUnit4.class)
public class ShamirSecretSharingTest {
    private final byte[] data;
    private final byte[] key;

    public ShamirSecretSharingTest() {
        data = new byte[ShamirSecretSharing.DATA_LEN];
        key = new byte[ShamirSecretSharing.KEY_LEN];
        Arrays.fill(data, (byte) 42);
        Arrays.fill(key, (byte) 42);
    }

    @Test
    public void Create_combine_shares_functional(){
        ArrayList<byte[]> shares;
        byte[] restored;

        shares = new ArrayList<>(Arrays.asList(ShamirSecretSharing.createShares(data, 5, 4)));
        restored = ShamirSecretSharing.combineShares(shares.toArray(new byte[][] {}));
        assertArrayEquals(restored, data);
        shares.remove(0);
        restored = ShamirSecretSharing.combineShares(shares.toArray(new byte[][] {}));
        assertArrayEquals(restored, data);
        shares.remove(0);
        restored = ShamirSecretSharing.combineShares(shares.toArray(new byte[][] {}));
        assertArrayEquals(restored, null);
        shares.remove(0);
        restored = ShamirSecretSharing.combineShares(shares.toArray(new byte[][] {}));
        assertArrayEquals(restored, null);
        shares.remove(0);
        restored = ShamirSecretSharing.combineShares(shares.toArray(new byte[][] {}));
        assertArrayEquals(restored, null);
        shares.remove(0);
        restored = ShamirSecretSharing.combineShares(shares.toArray(new byte[][] {}));
        assertArrayEquals(restored, null);
    }

    @Test
    public void Create_combine_keyshares_functional() {
        ArrayList<byte[]> keyshares;
        byte[] restored;

        keyshares = new ArrayList<>(Arrays.asList(ShamirSecretSharing.createKeyshares(key, 5, 4)));
        restored = ShamirSecretSharing.combineKeyshares(keyshares.toArray(new byte[][] {}));
        assertArrayEquals(restored, key);
        keyshares.remove(0);
        restored = ShamirSecretSharing.combineKeyshares(keyshares.toArray(new byte[][] {}));
        assertArrayEquals(restored, key);
        keyshares.remove(0);
        restored = ShamirSecretSharing.combineKeyshares(keyshares.toArray(new byte[][] {}));
        assertFalse(Arrays.equals(restored, key));
        keyshares.remove(0);
        restored = ShamirSecretSharing.combineKeyshares(keyshares.toArray(new byte[][] {}));
        assertFalse(Arrays.equals(restored, key));
        keyshares.remove(0);
        restored = ShamirSecretSharing.combineKeyshares(keyshares.toArray(new byte[][] {}));
        assertFalse(Arrays.equals(restored, key));
        keyshares.remove(0);
        restored = ShamirSecretSharing.combineKeyshares(keyshares.toArray(new byte[][] {}));
        assertFalse(Arrays.equals(restored, key));
    }

    @Test(expected = IllegalArgumentException.class)
    public void Create_shares_error_data_empty() {
        ShamirSecretSharing.createShares(new byte[] {}, 5, 4);
    }

    @Test(expected = IllegalArgumentException.class)
    public void Create_shares_error_data_len() {
        ShamirSecretSharing.createShares(key, 5, 4);
    }

    @Test(expected = IllegalArgumentException.class)
    public void Create_shares_error_count_lo() {
        ShamirSecretSharing.createShares(data, 0, 0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void Create_shares_error_count_hi() {
        ShamirSecretSharing.createShares(data, 256, 4);
    }

    @Test(expected = IllegalArgumentException.class)
    public void Create_shares_error_threshold_lo() {
        ShamirSecretSharing.createShares(data, 5, 0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void Create_shares_error_threshold_hi() {
        ShamirSecretSharing.createShares(data, 5, 6);
    }

    @Test(expected = IllegalArgumentException.class)
    public void Combine_shares_error_share_count() {
        ShamirSecretSharing.combineShares(new byte[256][ShamirSecretSharing.SHARE_LEN]);
    }

    @Test(expected = IllegalArgumentException.class)
    public void Create_keyshares_error_key_empty() {
        ShamirSecretSharing.createKeyshares(new byte[] {}, 5, 4);
    }

    @Test(expected = IllegalArgumentException.class)
    public void Create_keyshares_error_key_len() {
        ShamirSecretSharing.createKeyshares(data, 5, 4);
    }

    @Test(expected = IllegalArgumentException.class)
    public void Create_keyshares_error_count_lo() {
        ShamirSecretSharing.createKeyshares(key, 0, 0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void Create_keyshares_error_count_hi() {
        ShamirSecretSharing.createKeyshares(key, 256, 4);
    }

    @Test(expected = IllegalArgumentException.class)
    public void Create_keyshares_error_threshold_lo() {
        ShamirSecretSharing.createKeyshares(key, 5, 0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void Create_keyshares_error_threshold_hi() {
        ShamirSecretSharing.createKeyshares(key, 5, 6);
    }

    @Test(expected = IllegalArgumentException.class)
    public void Combine_keyshares_error_share_count() {
        ShamirSecretSharing.combineShares(new byte[256][ShamirSecretSharing.KEYSHARE_LEN]);
    }
}
