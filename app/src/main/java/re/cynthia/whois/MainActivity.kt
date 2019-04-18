package re.cynthia.whois

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.StrictMode
import android.text.method.ScrollingMovementMethod
import android.view.Menu
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_main.*
import java.net.URL

class MainActivity : AppCompatActivity() {

    var isInSubLayout = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val policy = StrictMode.ThreadPolicy.Builder().permitNetwork().build()
        StrictMode.setThreadPolicy(policy)

        setContentView(R.layout.activity_main)

        whoisResult.movementMethod = ScrollingMovementMethod()

        whoisSubmit.setOnClickListener {
            val res = URL(getString(R.string.api_url) + whoisQuery.text + "?client=android_app").readText()
            whoisResult.text = res
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == R.id.action_about) {
            setContentView(R.layout.layout_about)
            isInSubLayout = true
        }

        return true
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onBackPressed() {
        if(isInSubLayout) {
            setContentView(R.layout.activity_main)
            isInSubLayout = false
        } else {
            super.onBackPressed()
        }
    }

}
