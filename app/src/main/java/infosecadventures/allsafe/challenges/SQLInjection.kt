package infosecadventures.allsafe.challenges

import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.android.material.textfield.TextInputEditText
import infosecadventures.allsafe.R
import java.math.BigInteger
import java.security.MessageDigest


class SQLInjection : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {

        val view: View = inflater.inflate(R.layout.fragment_sql_injection, container, false)
        val db = populateDatabase()

        val username: TextInputEditText = view.findViewById(R.id.username)
        val password: TextInputEditText = view.findViewById(R.id.password)

        val login: Button = view.findViewById(R.id.login)
        login.setOnClickListener {

            val cursor: Cursor = db.rawQuery("select * from users where username = '" + username.text.toString() + "' and password = '" + md5(password.text.toString()) + "'", null)
            val data = StringBuilder()
            if (cursor.count > 0) {
                cursor.moveToFirst()
                do {
                    val user = cursor.getString(1)
                    val pass = cursor.getString(2)
                    data.append("User: $user \nPass: $pass\n")
                } while (cursor.moveToNext())
            }
            cursor.close()
            Toast.makeText(context, data, Toast.LENGTH_LONG).show()
        }
        return view
    }

    private fun md5(input: String): String {
        val md = MessageDigest.getInstance("MD5")
        return BigInteger(1, md.digest(input.toByteArray())).toString(16).padStart(32, '0')
    }

    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    

    private fun populateDatabase(): SQLiteDatabase {
        val db = requireActivity().openOrCreateDatabase("allsafe", android.content.Context.MODE_PRIVATE, null)
        db.execSQL("drop table if exists users")
        db.execSQL("create table users ( id integer primary key autoincrement, username text, password text )")
        db.execSQL("insert into users ( id, username, password ) values (0, 'admin', '21232f297a57a5a743894a0e4a801fc3')")
        db.execSQL("insert into users ( id, username, password ) values (1, 'elliot.alderson', '3484cef7f6ff172c2cd278d3b51f3e66')")
        db.execSQL("insert into users ( id, username, password ) values (2, 'angela.moss', '0af58729667eace3883a992ef2b8ce29')")
        db.execSQL("insert into users ( id, username, password ) values (3, 'gideon.goddard', '65dc3431f8c5e3f0e249c5b1c6e3534d')")
        db.execSQL("insert into users ( id, username, password ) values (4, 'tyrell.wellick', '6d2e1c6dd505a108cc7e19a46aa30a8a')")
        db.execSQL("insert into users ( id, username, password ) values (5, 'darlene.alderson', 'd510b80eb22f8eb684f1a19681eb7bcf')")
        return db
    }
}
