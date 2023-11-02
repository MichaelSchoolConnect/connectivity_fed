#include "include/connectivity_fed/connectivity_fed_plugin_c_api.h"

#include <flutter/plugin_registrar_windows.h>

#include "connectivity_fed_plugin.h"

void ConnectivityFedPluginCApiRegisterWithRegistrar(
    FlutterDesktopPluginRegistrarRef registrar) {
  connectivity_fed::ConnectivityFedPlugin::RegisterWithRegistrar(
      flutter::PluginRegistrarManager::GetInstance()
          ->GetRegistrar<flutter::PluginRegistrarWindows>(registrar));
}
