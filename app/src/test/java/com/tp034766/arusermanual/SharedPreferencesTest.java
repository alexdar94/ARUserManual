package com.tp034766.arusermanual;

import android.content.Context;
import android.content.SharedPreferences;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import static android.content.Context.MODE_PRIVATE;
import static org.junit.Assert.assertNotEquals;

/**
 * Created by User on 7/9/2017.
 */

@RunWith(MockitoJUnitRunner.class)
public class SharedPreferencesTest {
    @Mock
    SharedPreferences sharedPrefs;
    @Mock
    Context mMockContext;

    @Before
    public void before() throws Exception {
        this.sharedPrefs = Mockito.mock(SharedPreferences.class);
        this.mMockContext = Mockito.mock(Context.class);
        Mockito.when(mMockContext.getSharedPreferences("APPSP",MODE_PRIVATE)).thenReturn(sharedPrefs);
    }
    @Test
    public void shouldHaveSavedProduct() {
//        SharedPreferences sharedPreferences = Robolectric.application.getSharedPreferences("you_custom_pref_name", Context.MODE_PRIVATE);
//        sharedPreferences.edit().putString("testId", "12345").commit();
        String productData = sharedPrefs.getString("MYPRODUCTS", "");
        assertNotEquals( "", productData );
    }
}
