#ifndef FLUTTER_PLUGIN_CONNECTIVITY_FED_PLUGIN_H_
#define FLUTTER_PLUGIN_CONNECTIVITY_FED_PLUGIN_H_

#include <flutter/method_channel.h>
#include <flutter/plugin_registrar_windows.h>

#include <memory>

namespace connectivity_fed {

class ConnectivityFedPlugin : public flutter::Plugin {
 public:
  static void RegisterWithRegistrar(flutter::PluginRegistrarWindows *registrar);

  ConnectivityFedPlugin();

  virtual ~ConnectivityFedPlugin();

  // Disallow copy and assign.
  ConnectivityFedPlugin(const ConnectivityFedPlugin&) = delete;
  ConnectivityFedPlugin& operator=(const ConnectivityFedPlugin&) = delete;

 private:
  // Called when a method is called on this plugin's channel from Dart.
  void HandleMethodCall(
      const flutter::MethodCall<flutter::EncodableValue> &method_call,
      std::unique_ptr<flutter::MethodResult<flutter::EncodableValue>> result);
};

}  // namespace connectivity_fed

#endif  // FLUTTER_PLUGIN_CONNECTIVITY_FED_PLUGIN_H_
