package com.example.locationtracker

import android.Manifest
import android.content.Context
import android.content.Intent
import android.location.LocationManager
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings
import android.widget.Button
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.work.ExistingPeriodicWorkPolicy.REPLACE
import androidx.work.PeriodicWorkRequest
import androidx.work.PeriodicWorkRequestBuilder
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import permissions.dispatcher.NeedsPermission
import permissions.dispatcher.OnNeverAskAgain
import permissions.dispatcher.OnPermissionDenied
import permissions.dispatcher.OnShowRationale
import permissions.dispatcher.PermissionRequest
import permissions.dispatcher.RuntimePermissions
import androidx.work.WorkInfo
import androidx.work.WorkInfo.State.ENQUEUED
import androidx.work.WorkInfo.State.RUNNING
import androidx.work.WorkManager
import java.util.concurrent.TimeUnit

@RuntimePermissions
class LocationTrackerActivity : AppCompatActivity() {
  val TAG = "Location"
  lateinit var btn: Button
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_location_tracker)
    btn = findViewById(R.id.btn)
    checkWorkerMangerisWork()
    btn.setOnClickListener {
      if (it.tag==Status.START){
        getCurrentLocationWithPermissionCheck()
      }else{
        cancelWorkerManager()
      }
    }

  }

  private fun checkWorkerMangerisWork() {
    val info = WorkManager.getInstance(this).getWorkInfosByTag(TAG)
    if (isWorkScheduled(info.get())) {
      btn.setText("stop")
      btn.tag=Status.STOP
    } else {
      btn.setText("start")
      btn.tag=Status.START
    }
  }

  fun startWorkerPeriodic() {
    val myTrackLocationWork = PeriodicWorkRequestBuilder<LocationTrackerWorker>(
      20, TimeUnit.MINUTES, // repeatInterval (the period cycle)
      15, TimeUnit.MINUTES
    ) .addTag(TAG)// flexInterval
      .build()
    btn.setText("stop")
    WorkManager.getInstance(this).enqueueUniquePeriodicWork("tracer", REPLACE, myTrackLocationWork)
  }

  fun isLocationEnable(): Boolean {
    val locationManager =
      getSystemService(Context.LOCATION_SERVICE) as LocationManager
    return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) || locationManager.isProviderEnabled(
      LocationManager.NETWORK_PROVIDER
    )
  }

  private fun isWorkScheduled(workInfos: List<WorkInfo>?): Boolean {
    var running = false
    if (workInfos.isNullOrEmpty()) return false
    for (workStatus in workInfos) {
      running = (workStatus.state == RUNNING || workStatus.state == ENQUEUED)
    }
    return running
  }

  private fun cancelWorkerManager() {
    WorkManager.getInstance(this).cancelAllWorkByTag(TAG);
  }

  // MARK -- handle permission
  @NeedsPermission(Manifest.permission.ACCESS_FINE_LOCATION)
  fun getCurrentLocation() {
    if (isLocationEnable()) {
      startWorkerPeriodic()
    } else {
      MaterialAlertDialogBuilder(this)
        .setTitle(getString(R.string.location_not_enable))
        .setMessage(getString(R.string.enableLocation))
        .setPositiveButton(getString(R.string.enable)) { dialog, _ ->
          // open_setting_screen
          openSettingScree()
          dialog.dismiss()
        }
        .setNegativeButton(getString(R.string.deny)) { dialog, _ ->
          dialog.dismiss()
        }
        .show()
    }
  }

  private fun openSettingScree() {
    locationSettingScreen.launch(Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS))
  }

  private val applicationLocationSetting =
    registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
      getCurrentLocation()
    }
  private val locationSettingScreen =
    registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
      getCurrentLocation()
    }

  @OnShowRationale(Manifest.permission.ACCESS_FINE_LOCATION)
  fun showRationaleForLocation(request: PermissionRequest) {
    MaterialAlertDialogBuilder(this)
      .setMessage(getString(R.string.location_alert))
      .setPositiveButton(getString(R.string.accept)) { dialog, _ ->
        request.proceed()
        dialog.dismiss()
      }
      .setNegativeButton(getString(R.string.deny)) { dialog, _ ->
        request.cancel()
        dialog.dismiss()
      }
      .show()
  }

  @OnPermissionDenied(Manifest.permission.ACCESS_FINE_LOCATION)
  fun onDenyAskLocation() {
    Toast.makeText(this, getString(R.string.location_denied), Toast.LENGTH_SHORT).show()
  }

  @OnNeverAskAgain(Manifest.permission.ACCESS_FINE_LOCATION)
  fun onLocationNeverAskAgain() {
    val openLocationSetting = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
    openLocationSetting.data = Uri.fromParts("package", packageName, null)
    applicationLocationSetting.launch(openLocationSetting)
  }

  override fun onRequestPermissionsResult(
    requestCode: Int,
    permissions: Array<out String>,
    grantResults: IntArray
  ) {
    this.onRequestPermissionsResult(requestCode, grantResults)
    super.onRequestPermissionsResult(requestCode, permissions, grantResults)
  }

  // MARK end
}