package infosecadventures.allsafe.challenges

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.scottyab.rootbeer.RootBeer
import infosecadventures.allsafe.R
import infosecadventures.allsafe.utils.SnackUtil

class RootDetection : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {



        val view = inflater.inflate(R.layout.fragment_root_detection, container, false)

        view.findViewById<View>(R.id.check).setOnClickListener {
            checkRoot(RootBeer(context).isRooted);
        }
        return view
    }

    private fun checkRoot(root:Boolean)  {
        
        if (root) {
            SnackUtil.simpleMessage(requireActivity(), "Sorry, your device is rooted!")
        } else {
            SnackUtil.simpleMessage(requireActivity(), "Congrats, root is not detected!")
        }
    }
}