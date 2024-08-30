package com.example.fragmentado

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity.LOCATION_SERVICE
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment

class LocationFragment : Fragment() {
    private var latitude: String = ""
    private var longitude: String = ""

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_location, container, false)

        val btnGPS: Button = view.findViewById(R.id.btn_gps)
        val btnMaps: Button = view.findViewById(R.id.btn_localizacaoWeb)
        val txtInfo: TextView = view.findViewById(R.id.txtInfo)
        val txtLatitude: TextView = view.findViewById(R.id.txtLatitude)
        val txtLongitude: TextView = view.findViewById(R.id.txtLongitude)

        txtInfo.visibility = View.INVISIBLE
        btnMaps.visibility = View.INVISIBLE
        btnMaps.isEnabled = false
        txtLatitude.visibility = View.INVISIBLE
        txtLongitude.visibility = View.INVISIBLE

        btnGPS.setOnClickListener {
            pedirPermissoes()
            Handler(Looper.getMainLooper()).postDelayed({
                txtInfo.visibility = View.VISIBLE
                btnMaps.visibility = View.VISIBLE
                btnMaps.isEnabled = true
                txtLatitude.visibility = View.VISIBLE
                txtLongitude.visibility = View.VISIBLE
            }, 3000)
        }

        btnMaps.setOnClickListener {
            openBrowser(
                "https://www.google.com/maps/place/+" + latitude + "+" + longitude +
                        "+/@-10.4030851,-67.5302886,3z/data=!4m4!3m3!8m2!" +
                        "3d-3.691!4d-39.5802?entry=ttu&g_ep=EgoyMDI0MDgyMC4xIKXMDSoASAFQAw" +
                        "%3D%3D"
            )
        }
        return view
    }

    override fun onResume() {
        super.onResume()
        pedirPermissoes()
    }

    private fun pedirPermissoes() {
        if (ActivityCompat.checkSelfPermission(
                requireActivity(),
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
            && ActivityCompat.checkSelfPermission(
                requireActivity(),
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                requireActivity(),
                arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                1
            )
        } else configurarServico()
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>, grantResults: IntArray
    ) {
        requireActivity().onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            1 -> {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager
                        .PERMISSION_GRANTED
                ) {
                    configurarServico()
                } else {
                    Toast.makeText(
                        requireActivity(), "Não vai funcionar!!!", Toast
                            .LENGTH_LONG
                    ).show()
                }
                return
            }
        }
    }

    private fun configurarServico() {
        try {
            val locationManager =
                requireActivity().getSystemService(LOCATION_SERVICE) as LocationManager

            val locationListener: LocationListener = object : LocationListener {
                override fun onLocationChanged(location: Location) {
                    atualizar(location)
                }

                override fun onStatusChanged(provider: String, status: Int, extras: Bundle) {
                }

                override fun onProviderEnabled(provider: String) {}

                override fun onProviderDisabled(provider: String) {}
            }
            locationManager.requestLocationUpdates(
                LocationManager.GPS_PROVIDER,
                1000,
                0f,
                locationListener
            )
        } catch (ex: SecurityException) {
            Toast.makeText(requireActivity(), ex.message, Toast.LENGTH_LONG).show()
        }
    }

    fun atualizar(location: Location) {
        val latPoint: Double = location.latitude
        val lngPoint: Double = location.longitude

        latitude = latPoint.toString()
        longitude = lngPoint.toString()

        val txtLatitude: TextView? = view?.findViewById(R.id.txtLatitude)
        val txtLongitude: TextView? = view?.findViewById(R.id.txtLongitude)

        txtLatitude?.text = latitude
        txtLongitude?.text = longitude
    }

    private fun openBrowser(url: String) {
        val webpage: Uri = Uri.parse(url)
        val iUri = Intent(Intent.ACTION_VIEW, webpage)
        startActivity(iUri)
    }

}