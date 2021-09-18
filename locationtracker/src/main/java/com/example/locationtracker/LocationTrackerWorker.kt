package com.example.locationtracker

import android.annotation.SuppressLint
import android.content.Context
import android.location.Location
import android.os.Looper
import android.util.Log
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationServices

class LocationTrackerWorker(private val appContext: Context,private val workerParams: WorkerParameters) :Worker(appContext,workerParams) {
   private lateinit var fusedLocationClient: FusedLocationProviderClient
  var locationCallback: LocationCallback? = null
  var locationRequest: LocationRequest? = null
  val TAG="LocationTrackerWorker"
  @SuppressLint("MissingPermission")
  fun getLastLocation(onLocationAvailable: (Location) -> Unit) {
    fusedLocationClient.lastLocation.addOnSuccessListener { location: Location? ->
      if (location != null) {
        onLocationAvailable(location)
      } else {
        createReauestLocation(onLocationAvailable)
      }
    }
  }
  private fun createReauestLocation(onLocationAvailable: (Location) -> Unit) {
    locationRequest = LocationRequest.create().apply {
      interval = 10000
      fastestInterval = 5000
      priority = LocationRequest.PRIORITY_HIGH_ACCURACY
    }
    locationCallback = object : LocationCallback() {
      override fun onLocationResult(locationResult: LocationResult?) {
        locationResult ?: return
        for (location in locationResult.locations) {
          onLocationAvailable(location)
        }
      }
    }
  }

  override fun onStopped() {
    super.onStopped()
    stopLocationUpdates()
  }


  private fun stopLocationUpdates() {
    if (locationCallback != null)
      fusedLocationClient.removeLocationUpdates(locationCallback)
  }
  @SuppressLint("MissingPermission")
  private fun startLocationUpdates() {
    if (locationCallback != null && locationRequest != null)
      fusedLocationClient.requestLocationUpdates(
        locationRequest,
        locationCallback,
        Looper.getMainLooper()
      )
  }

  override fun doWork(): Result {
    fusedLocationClient= LocationServices.getFusedLocationProviderClient(appContext)
    Log.e(TAG, "doWork: Done");
    Log.e(TAG, "onStartJob: STARTING JOB..");
    startLocationUpdates()
    getLastLocation {
      val notificationHelper=NotificationHelper(appContext)
      notificationHelper.notify(100,notificationHelper.getNotification("new locationUpdate","latitude : ${it.latitude}   longitude : {${it.longitude}}",null,R.mipmap.ic_launcher)!!)
     // stopLocationUpdates()
    }
    return Result.success()
  }
}