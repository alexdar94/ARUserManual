package com.tp034766.arusermanual;

import org.junit.Test;
import org.robolectric.annotation.Config;
import org.robolectric.manifest.AndroidManifest;
import org.robolectric.res.Fs;

import java.util.HashSet;

import static com.google.common.truth.Truth.assertThat;

/**
 * Created by User on 7/9/2017.
 */
//@RunWith(RobolectricTestRunner.class)
@Config(manifest = Config.NONE)
public final class AppPermissionTest {

    private static final String[] EXPECTED_PERMISSIONS = {
            "android.permission.CAMERA","android.permission.WRITE_EXTERNAL_STORAGE"
            ,"android.permission.READ_PHONE_STATE","android.permission.READ_EXTERNAL_STORAGE"
    };

    private static final String MERGED_MANIFEST =
            "build/intermediates/manifests/full/debug/AndroidManifest.xml";

    @Test
    public void shouldMatchPermissions() {
        AndroidManifest manifest = new AndroidManifest(
                Fs.fileFromPath(MERGED_MANIFEST),
                null,
                null
        );
        assertThat(new HashSet<>(manifest.getUsedPermissions())).containsExactly(EXPECTED_PERMISSIONS);
    }
}