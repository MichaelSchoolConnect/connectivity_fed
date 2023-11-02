import 'package:flutter/foundation.dart';
import 'package:flutter/services.dart';

import 'connectivity_fed_platform_interface.dart';

/// An implementation of [ConnectivityFedPlatform] that uses method channels.
class MethodChannelConnectivityFed extends ConnectivityFedPlatform {
  /// The method channel used to interact with the native platform.
  @visibleForTesting
  final methodChannel = const MethodChannel('connectivity_fed');

  @override
  Future<String?> getPlatformVersion() async {
    final version = await methodChannel.invokeMethod<String>('getPlatformVersion');
    return version;
  }
}
