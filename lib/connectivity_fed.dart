import 'dart:async';

enum ConnectivityResult {
  wifi,
  mobile,
  none,
}

abstract class ConnectivityFed {
  Future<ConnectivityResult> checkConnectivity();
  Stream<ConnectivityResult> get onConnectivityChanged;
}

class ConnectivityFedPlugin implements ConnectivityFed {
  static ConnectivityFedPlugin _instance;

  static ConnectivityFedPlugin get instance {
    _instance ??= ConnectivityFedPlugin._();
    return _instance;
  }

  ConnectivityFedPlugin._();

  StreamController<ConnectivityResult> _connectivityResultController =
      StreamController.broadcast();

  @override
  Future<ConnectivityResult> checkConnectivity() {
    // Platform-specific implementation will be called here
    throw UnimplementedError('checkConnectivity() has not been implemented.');
  }

  @override
  Stream<ConnectivityResult> get onConnectivityChanged =>
      _connectivityResultController.stream;

  // This should be called by the platform-specific code when connectivity changes.
  void updateConnectivity(ConnectivityResult result) {
    _connectivityResultController.add(result);
  }
}
