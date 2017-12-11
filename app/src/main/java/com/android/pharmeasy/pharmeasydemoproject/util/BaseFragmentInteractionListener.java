package com.android.pharmeasy.pharmeasydemoproject.util;

import android.os.Bundle;
import android.support.v4.app.Fragment;

/**
 * Created by user on 12/10/2017.
 */

public interface BaseFragmentInteractionListener {
    <T extends Fragment> void showFragment(Class<T> fragmentClass, Bundle bundle,
                                           boolean addToBackStack);
}
