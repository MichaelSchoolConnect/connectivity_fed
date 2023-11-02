package com.example.connectivity_fed

import io.flutter.embedding.engine.plugins.FlutterPlugin
import io.flutter.plugin.common.EventChannel
import io.flutter.plugin.common.MethodChannel
import android.net.ConnectivityManager
import android.content.Context
import android.os.Handler
import android.os.Looper

class ConnectivityFedPlugin: FlutterPlugin, EventChannel.StreamHandler {
  private lateinit var channel : MethodChannel
  private lateinit var eventChannel : EventChannel
  private lateinit var context: Context
  private var eventSink: EventChannel.EventSink? = null
  private val mainThreadHandler = Handler(Looper.getMainLooper())

  private val connectivityChangeReceiver = object : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
      val status = getConnectivityStatusString(context)
      mainThreadHandler.post { eventSink?.success(status) }
    }
  }

  override fun onAttachedToEngine(flutterPluginBinding: FlutterPlugin.FlutterPluginBinding) {
    context = flutterPluginBinding.applicationContext
    eventChannel = EventChannel(flutterPluginBinding.binaryMessenger, "connectivity_fed_status")
    eventChannel.setStreamHandler(this)

    // Register for connectivity changes
    context.registerReceiver(
      connectivityChangeReceiver,
      IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION)
    )
  }

  override fun onListen(arguments: Any?, events: EventChannel.EventSink?) {
    eventSink = events
    // Set up connectivity broadcast receiver and start listening for changes
    // Call `events.success(status)` whenever connectivity status changes
    // Immediately send the current connectivity status
    val status = getConnectivityStatusString(context)
    events?.success(status)
  }

  override fun onCancel(arguments: Any?) {
    eventSink = null
    // Clean up connectivity broadcast receiver
    // Unregister connectivity changes
    context.unregisterReceiver(connectivityChangeReceiver)
  }

  override fun onDetachedFromEngine(binding: FlutterPlugin.FlutterPluginBinding) {
    eventChannel.setStreamHandler(null)
  }

  // Implement checkConnectivity using Android's ConnectivityManager
  // Utility method to check the current connectivity status
  private fun getConnectivityStatusString(context: Context?): String {
    val cm = context?.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val activeNetwork = cm.activeNetwork
    val capabilities = cm.getNetworkCapabilities(activeNetwork)
    return when {
      capabilities?.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) == true -> "WIFI"
      capabilities?.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) == true -> "CELLULAR"
      else -> "NONE"
    }
  }
}
