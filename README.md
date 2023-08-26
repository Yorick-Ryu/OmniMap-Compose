OmniMap Compose 🗺
===============
<a href="https://github.com/TheMelody/OmniMap-Compose/blob/main/LICENSE"><img alt="LICENSE" src="https://img.shields.io/github/license/TheMelody/OmniMap-Compose"/></a>  <img alt="issues" src="https://img.shields.io/github/issues/TheMelody/OmniMap-Compose?color=important"/>  <img alt="forks" src="https://img.shields.io/github/forks/TheMelody/OmniMap-Compose?color=blueviolet"/>  <img alt="stars" src="https://img.shields.io/github/stars/TheMelody/OmniMap-Compose?color=success"/>  <a href="https://juejin.cn/user/8451824316670/posts"><img alt="稀土掘金" src="https://img.shields.io/badge/%E7%A8%80%E5%9C%9F%E6%8E%98%E9%87%91-432-green?labelColor=%231e80FF&color=black" ></a>  <a href="https://www.zhihu.com/people/fq_halifax"><img src="https://img.shields.io/badge/dynamic/json?color=282c34&amp;labelColor=0084ff&amp;label=%E7%9F%A5%E4%B9%8E&amp;query=%24.data.totalSubs&amp;url=https%3A%2F%2Fapi.spencerwoo.com%2Fsubstats%2F%3Fsource%3Dzhihu%26queryKey%3Dfq_halifax&amp;longCache=true" alt="知乎"></a>  <a href="https://blog.csdn.net/logicsboy"><img src="https://img.shields.io/badge/CSDN-3k+-red?labelColor=%231e80FF&color=black" alt="CSDN"></a>

**Compose一键集成5大地图神器**

<a href="https://lbsyun.baidu.com/index.php?title=androidsdk"><img src="https://img.shields.io/badge/-%E7%99%BE%E5%BA%A6%E5%9C%B0%E5%9B%BE-4e6ef2"></a>    <a href="https://lbs.amap.com/api/android-sdk/summary/"><img src="https://img.shields.io/badge/-%E9%AB%98%E5%BE%B7%E5%9C%B0%E5%9B%BE-success"></a>    <a href="https://lbs.qq.com/mobile/androidMapSDK/developerGuide/androidSummary"><img src="https://img.shields.io/badge/-%E8%85%BE%E8%AE%AF%E5%9C%B0%E5%9B%BE-E91E1E"></a>    <a href="https://developer.huawei.com/consumer/cn/doc/development/HMSCore-Guides/android-sdk-brief-introduction-0000001061991343"><img src="https://img.shields.io/badge/-%E8%8A%B1%E7%93%A3%E5%9C%B0%E5%9B%BE-orange"></a>    <a href="https://developers.google.com/maps/documentation/android-sdk/start?hl=zh-cn"><img src="https://img.shields.io/badge/-Google%E5%9C%B0%E5%9B%BE-blue"></a>

集成
-------
<table>
 <tr>
  <td>gd_compose</td><td><img alt="Maven Central" src="https://img.shields.io/maven-central/v/io.github.TheMelody/gd_compose?versionPrefix=1.0.5"></td>
 </tr>
 <tr>
  <td>tencent_compose</td><td><img alt="Maven Central" src="https://img.shields.io/maven-central/v/io.github.TheMelody/tencent_compose?versionPrefix=1.0.5"></td>
 </tr>
 <tr>
  <td>baidu_compose</td><td><img alt="Maven Central" src="https://img.shields.io/maven-central/v/io.github.TheMelody/baidu_compose?versionPrefix=1.0.5"></td>
 </tr>
</table>

```groovy
repositories {
  maven { url 'https://mirrors.tencent.com/nexus/repository/maven-public/' }
}

dependencies {
  // 根据自己项目情况，选择下面其中一种地图
  implementation 'io.github.TheMelody:gd_compose:<version>'       // 高德地图
  implementation 'io.github.TheMelody:tencent_compose:<version>'  // 腾讯地图
  implementation 'io.github.TheMelody:baidu_compose:<version>'    // 百度地图
  implementation 'io.github.TheMelody:huawei_compose:<version>'   // 花瓣地图(Android 7.0+) → 实现中
  implementation 'io.github.TheMelody:google_compose:<version>'   // Google地图 → 未开始
}
```

用法
-------

- 1、添加一个高德地图
```kt
val cameraPositionState = rememberCameraPositionState {
    position = CameraPosition.fromLatLngZoom(LatLng(39.984108,116.307557), 10F)
}
GDMap(
    modifier = Modifier.fillMaxSize(),
    cameraPositionState = cameraPositionState
){
    //这里面放地图覆盖物...
}
```
- 2、添加一个腾讯地图
```kt
val cameraPositionState = rememberCameraPositionState {
   position =  TXCameraPosition(latLng = LatLng(39.984108,116.307557), zoom = 10F, tilt = 0F, bearing = 0F)
}
TXMap(
    modifier = Modifier.fillMaxSize(),
    cameraPositionState = cameraPositionState
){
    //这里面可以放地图覆盖物...
}
```
- 3、添加一个百度地图
```kt
val cameraPositionState = rememberCameraPositionState {
    position = BDCameraPosition(LatLng(39.984108,116.307557), 4F, 0f, 0f)
}
BDMap(
    modifier = Modifier.fillMaxSize(),
    cameraPositionState = cameraPositionState
){
    //这里面可以放地图覆盖物...
}
```
- 4、配置地图
```kt
// 高德地图
GDMap(
    modifier = Modifier.fillMaxSize(),
    properties = MapProperties(/**自行修改参数**/),
    uiSettings  = MapUiSettings(/**自行修改参数**/)
){
    //这里面可以放地图覆盖物...
}

//腾讯地图
TXMap(
    modifier = Modifier.fillMaxSize(),
    properties = MapProperties(/**自行修改参数**/),
    uiSettings  = MapUiSettings(/**自行修改参数**/)
){
    //这里面可以放地图覆盖物...
}

//百度地图
BDMap(
    modifier = Modifier.fillMaxSize(),
    properties = MapProperties(/**自行修改参数**/),
    uiSettings  = MapUiSettings(/**自行修改参数**/)
){
    //这里面可以放地图覆盖物...
}
```
- 5、自定义Marker覆盖物的InfoWindow
```kt
// 只修改内容，不修改容器
MarkerInfoWindowContent(
    // ...
    title = "我是title",
    snippet = "我是snippet"
) { marker ->
    Column {
        Text(marker.title ?: "", color = Color.Green)
        Text(marker.snippet ?: "", color = Color.Red)
        // TODO: 如果是百度地图，请使用 marker.getTitleExt() 和 marker.getSnippetExt()
    }
}

// 修改整个信息窗(容器及内容)
MarkerInfoWindow(
    //...
    snippet = "我是一个卖报的小画家(自定义InfoWindow)"
) { marker ->
    Card(modifier = Modifier.requiredSizeIn(maxWidth = 88.dp, minHeight = 66.dp)) {
        Text(
            modifier = Modifier.padding(4.dp),
            text = marker.snippet ?: "", color = Color.Red)
        // TODO: 如果是百度地图，请使用 marker.getSnippetExt()
    }
}
```
- 6、已支持的覆盖物
<table>
 <tr>
  <td width="66px">高德地图</td> <td>Arc、Circle、ClusterOverlay、GroundOverlay、Marker、MovingPointOverlay、MultiPointOverlay、OpenGLOverlay、Polygon、Polyline、RoutePlanOverlay、TileOverlay</td>
 </tr>
 <tr>
  <td width="66px">腾讯地图</td> <td>Arc、Circle、ClusterOverlay、GroundOverlay、Marker、MovingPointOverlay、Polygon、Polyline、TileOverlay</td>
 </tr>
 <tr>
  <td width="66px">百度地图</td> <td>Arc、Circle、ClusterOverlay、GroundOverlay、Marker、MultiPointOverlay、Polygon、Polyline、TileOverlay、RoutePlanOverlay、TextOverlay、TraceOverlay、BM3DBuildOverlay、BM3DModelOverlay、BM3DPrismOverlay</td>
 </tr>
</table>

**更多能力，请查阅我们的示例Demo**

License
-------
```
MIT License

Copyright (c) 2023 被风吹过的夏天

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
```
