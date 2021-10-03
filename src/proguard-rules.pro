# Add any ProGuard configurations specific to this
# extension here.

-keep public class com.vedang.mixpanelanalytics.MixpanelAnalytics {
    public *;
 }
-keeppackagenames gnu.kawa**, gnu.expr**

-optimizationpasses 4
-allowaccessmodification
-mergeinterfacesaggressively

-repackageclasses 'com/vedang/mixpanelanalytics/repack'
-flattenpackagehierarchy
-dontpreverify
