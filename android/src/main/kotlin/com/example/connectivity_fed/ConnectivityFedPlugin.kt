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

  override fun onAttachedToEngine(flutterPluginBinding: FlutterPlugin.FlutterPluginBinding) {
    context = flutterPluginBinding.applicationContext
    eventChannel = EventChannel(flutterPluginBinding.binaryMessenger, "connectivity_fed_status")
    eventChannel.setStreamHandler(this)
  }

  override fun onListen(arguments: Any?, events: EventChannel.EventSink?) {
    eventSink = events
    // Set up connectivity broadcast receiver and start listening for changes
    // Call `events.success(status)` whenever connectivity status changes
  }

  override fun onCancel(arguments: Any?) {
    eventSink = null
    // Clean up connectivity broadcast receiver
  }

  override fun onDetachedFromEngine(binding: FlutterPlugin.FlutterPluginBinding) {
    eventChannel.setStreamHandler(null)
  }

  /// The MethodChannel that will the communication between Flutter and native Android
///
/// This local reference serves to register the plugin with the Flutter Engine and unregister it
/// when the Flutter Engine is detached from the Activity
  private lateinit var channel : MethodChannel

  override fun onAttachedToEngine(@NonNull flutterPluginBinding: FlutterPlugin.FlutterPluginBinding) {
    channel = MethodChannel(flutterPluginBinding.binaryMessenger, "connectivity_fed")
    channel.setMethodCallHandler(this)
  }

  override fun onMethodCall(@NonNull call: MethodCall, @NonNull result: Result) {
    if (call.method == "getPlatformVersion") {
      result.success("Android ${android.os.Build.VERSION.RELEASE}")
    } else {
      result.notImplemented()
    }
  }

  override fun onDetachedFromEngine(@NonNull binding: FlutterPlugin.FlutterPluginBinding) {
    channel.setMethodCallHandler(null)
  }


  // Implement checkConnectivity using Android's ConnectivityManager
}
