package benedykt.ziobro.cv

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import benedykt.ziobro.cv.extension.replace
import benedykt.ziobro.cv.fragment.CvFragment
import benedykt.ziobro.cv.fragment.CvPageFragment
import org.koin.androidx.fragment.android.setupKoinFragmentFactory

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupKoinFragmentFactory()
        setContentView(R.layout.activity_main)

        supportFragmentManager
            .beginTransaction()
            .replace<CvPageFragment>(R.id.fragment_container)
            .commit()
    }

}