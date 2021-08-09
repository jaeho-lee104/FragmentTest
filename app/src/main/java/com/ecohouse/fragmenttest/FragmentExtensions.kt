package com.ecohouse.fragmenttest

import androidx.annotation.IdRes
import androidx.fragment.app.Fragment


/**
 * @author jaeho.lee104 on 2021. 08. 02..
 */

fun Fragment.addChildFragment(
    @IdRes childContainerResId: Int,
    childFragment: Fragment,
    tag: String
) {
    parentFragmentManager.beginTransaction()
        .add(childContainerResId, childFragment, tag)
        .addToBackStack(childFragment.tag)
        .commitAllowingStateLoss()
}

fun Fragment.popSelf() = parentFragmentManager.popBackStack()