import 'package:flutter_test/flutter_test.dart';
import 'package:connectivity_fed/connectivity_fed.dart';
import 'package:connectivity_fed/connectivity_fed_platform_interface.dart';
import 'package:connectivity_fed/connectivity_fed_method_channel.dart';
import 'package:plugin_platform_interface/plugin_platform_interface.dart';

class MockConnectivityFedPlatform
    with MockPlatformInterfaceMixin
    implements ConnectivityFedPlatform {

  @override
  Future<String?> getPlatformVersion() => Future.value('42');
}

void main() {
  final ConnectivityFedPlatform initialPlatform = ConnectivityFedPlatform.instance;

  test('$MethodChannelConnectivityFed is the default instance', () {
    expect(initialPlatform, isInstanceOf<MethodChannelConnectivityFed>());
  });

  test('getPlatformVersion', () async {
    ConnectivityFed connectivityFedPlugin = ConnectivityFed();
    MockConnectivityFedPlatform fakePlatform = MockConnectivityFedPlatform();
    ConnectivityFedPlatform.instance = fakePlatform;

    expect(await connectivityFedPlugin.getPlatformVersion(), '42');
  });
}
