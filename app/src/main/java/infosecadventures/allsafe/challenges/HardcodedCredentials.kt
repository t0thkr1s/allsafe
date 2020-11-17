package infosecadventures.allsafe.challenges

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import infosecadventures.allsafe.R
import infosecadventures.allsafe.utils.SnackUtil
import okhttp3.*
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody.Companion.toRequestBody
import java.io.IOException

class HardcodedCredentials : Fragment() {


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_hardcoded_credentials, container, false)
        val request: Button = view.findViewById(R.id.request)
        request.setOnClickListener {
            val client = OkHttpClient()
            val body: RequestBody = BODY.toRequestBody(SOAP)
            val req: Request = Request.Builder()
                    .url(getString(R.string.dev_env))
                    .post(body)
                    .build()

            client.newCall(req).enqueue(object : Callback {
                override fun onResponse(call: Call, response: Response) {
                    // todo
                }

                override fun onFailure(call: Call, e: IOException) {
                    // todo
                }
            })
            SnackUtil.simpleMessage(requireActivity(), "Under development!")
        }
        return view
    }

    companion object {
        val SOAP = "application/soap+xml; charset=utf-8".toMediaTypeOrNull()
        const val BODY = """
            <soap:Envelope xmlns:soap="http://schemas.xmlsoap.org/soap/envelope/">
            <soap:Header>
                 <UsernameToken xmlns="http://siebel.com/webservices">superadmin</UsernameToken>
                 <PasswordText xmlns="http://siebel.com/webservices">supersecurepassword</PasswordText>
                 <SessionType xmlns="http://siebel.com/webservices">None</SessionType>
            </soap:Header>
            <soap:Body>
                 <!-- data goes here -->
            </soap:Body>
            </soap:Envelope>
        """
    }

}