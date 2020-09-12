package benedykt.ziobro.cv.extension

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction

inline fun <reified T : Fragment> FragmentTransaction.replace(
    fragmentContainer: Int,
    args: Bundle? = null,
    tag: String? = null,
): FragmentTransaction {
   return replace(fragmentContainer, T::class.java, args, tag)
}