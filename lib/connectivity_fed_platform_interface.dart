import 'package:plugin_platform_interface/plugin_platform_interface.dart';

import 'connectivity_fed_method_channel.dart';

abstract class ConnectivityFedPlatform extends PlatformInterface {
  /// Constructs a ConnectivityFedPlatform.
  ConnectivityFedPlatform() : super(token: _token);

  static final Object _token = Object();

  static ConnectivityFedPlatform _instance = MethodChannelConnectivityFed();

  /// The default instance of [ConnectivityFedPlatform] to use.
  ///
  /// Defaults to [MethodChannelConnectivityFed].
  static ConnectivityFedPlatform get instance => _instance;

  /// Platform-specific implementations should set this with their own
  /// platform-specific class that extends [ConnectivityFedPlatform] when
  /// they register themselves.
  static set instance(ConnectivityFedPlatform instance) {
    PlatformInterface.verifyToken(instance, _token);
    _instance = instance;
  }

  Future<String?> getPlatformVersion() {
    throw UnimplementedError('platformVersion() has not been implemented.');
  }
}
