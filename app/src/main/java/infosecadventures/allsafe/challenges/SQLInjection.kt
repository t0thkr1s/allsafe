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

            val cursor: Cursor = db.rawQuery("select * from users where username = '" + username + "' and password = '" + password + "'", null)

            if (cursor.count > 0) {
                cursor.moveToFirst()
                do {
                    val id = cursor.getString(0)
                    val user = cursor.getString(1)
                    val pw = cursor.getString(2)
                    Toast.makeText(context, "ID: " + id + " User: " + user, Toast.LENGTH_LONG).show()
                } while (cursor.moveToNext())
            }
            cursor.close()

        }
        return view
    }

    private fun md5(str: String): ByteArray = MessageDigest.getInstance("MD5").digest(str.toByteArray(Charsets.UTF_8))
    private fun populateDatabase(): SQLiteDatabase {
        val db = requireActivity().openOrCreateDatabase("allsafe", android.content.Context.MODE_PRIVATE, null)
        db.execSQL("drop table if exists users")
        db.execSQL("create table users ( id integer primary key autoincrement, username text, password text )")
        db.execSQL("insert into users ( id, username, password ) values (0, 'admin', 'b90708413dbbd54a670ab3f3f9026f9b')")
        db.execSQL("insert into users ( id, username, password ) values (1, 'elliot.alderson', '3484cef7f6ff172c2cd278d3b51f3e66')")
        db.execSQL("insert into users ( id, username, password ) values (2, 'angela.moss', '0af58729667eace3883a992ef2b8ce29')")
        db.execSQL("insert into users ( id, username, password ) values (3, 'gideon.goddard', '65dc3431f8c5e3f0e249c5b1c6e3534d')")
        db.execSQL("insert into users ( id, username, password ) values (4, 'tyrell.wellick', '6d2e1c6dd505a108cc7e19a46aa30a8a')")
        db.execSQL("insert into users ( id, username, password ) values (5, 'darlene.alderson', 'd510b80eb22f8eb684f1a19681eb7bcf')")
        return db
    }
}