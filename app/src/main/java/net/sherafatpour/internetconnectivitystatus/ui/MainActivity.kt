package net.sherafatpour.internetconnectivitystatus.ui

import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import android.view.Menu
import android.view.MenuItem
import androidx.annotation.RequiresApi
import androidx.lifecycle.lifecycleScope
import com.airbnb.lottie.LottieDrawable
import dagger.hilt.android.AndroidEntryPoint
import net.sherafatpour.internetconnectivitystatus.R
import net.sherafatpour.internetconnectivitystatus.databinding.ActivityMainBinding
import net.sherafatpour.internetconnectivitystatus.di.qualifiers.QualifierAvailable
import net.sherafatpour.internetconnectivitystatus.di.qualifiers.QualifierLosing
import net.sherafatpour.internetconnectivitystatus.di.qualifiers.QualifierLost
import net.sherafatpour.internetconnectivitystatus.di.qualifiers.QualifierUnAvailable
import net.sherafatpour.internetconnectivitystatus.util.connection.ConnectivityObserver
import net.sherafatpour.internetconnectivitystatus.util.connection.ConnectivityObserver.Status.*
import net.sherafatpour.internetconnectivitystatus.util.connection.InternetConnectivityObserver
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    lateinit var connectivity: InternetConnectivityObserver

    @Inject
    //@Named("Available")
    @QualifierAvailable
    lateinit var available : ConnectivityObserver.Status

    @Inject
    //@Named("Losing")
    @QualifierLosing
    lateinit var losing : ConnectivityObserver.Status

    @Inject
    //@Named("Lost")
    @QualifierLost
    lateinit var lost : ConnectivityObserver.Status

    @Inject
    //@Named("Unavailable")
    @QualifierUnAvailable
    lateinit var unavailable : ConnectivityObserver.Status







    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        WindowCompat.setDecorFitsSystemWindows(window, false)
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //init
        available = Available
        unavailable =Unavailable
        lost = Lost
        losing = Losing


        connectivity = InternetConnectivityObserver(applicationContext)
        binding.content.animationView.repeatCount= LottieDrawable.INFINITE
        binding.content.animationView.playAnimation()


        lifecycleScope.launchWhenStarted{
            connectivity.observe().collect() {

                when (it) {
                    Available -> {
                        binding.content.animationView.setAnimation("online.json")
                        binding.content.animationView.playAnimation()
                        //binding.content.txtStatus.text = "Connectivity Available"
                        binding.content.txtStatus.text = "Connectivity" + available

                    }

                    Unavailable -> {
                        binding.content.animationView.setAnimation("disconnect.json")
                        binding.content.animationView.playAnimation()
                        //binding.content.txtStatus.text = "Connectivity Unavailable"
                        binding.content.txtStatus.text = "Connectivity" + unavailable



                    }

                    Losing -> {
                        binding.content.animationView.setAnimation("disconnect.json")
                        binding.content.animationView.playAnimation()
                        binding.content.txtStatus.text = "Connectivity" + losing

                    }

                    Lost -> {
                        binding.content.animationView.setAnimation("disconnect.json")
                        binding.content.animationView.playAnimation()
                        //binding.content.txtStatus.text = "Connectivity is Lost"
                        binding.content.txtStatus.text = "Connectivity" + lost

                    }

                }
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }
}