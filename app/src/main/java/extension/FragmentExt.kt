package extension

import android.util.Log
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.NavDirections
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController

private const val TAG = "FragmentLiveDataUtils"
fun Fragment.navigateObserver(
    directions: NavDirections,
    navOptions: NavOptions? = null
): Observer<in Unit> = Observer {
   Log.d(TAG, "navigateObserver: received directions to ${resources.getResourceEntryName(directions.actionId)}")

    if (navOptions == null) findNavController().navigate(directions)
    else findNavController().navigate(directions, navOptions)
}