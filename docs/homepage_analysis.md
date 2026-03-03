# RoSupportCalculator 首頁分析

## 啟動流程
1. App 啟動入口是 `LogoActivity`（在 `AndroidManifest.xml` 設為 `MAIN/LAUNCHER`）。
2. `LogoActivity` 顯示 `activity_logo.xml`，整頁是一張 `@drawable/logo`。
3. 在 `onResume()` 中延遲 500ms 後，跳轉到 `MainActivity`。

## 首頁（MainActivity）結構
`MainActivity` 綁定 `activity_main.xml`，頁面包含：
- 頂部 `action_bar`：中間顯示 logo，右側顯示 menu 按鈕（back/home 隱藏）。
- 主要內容區（NestedScrollView）：
  - 標題 `My Role`
  - 按鈕 `New Role`
  - 角色列表 `rv_roles`（目前尚未設定 adapter）
  - 標題 `熱門裝備`
  - 熱門裝備列表 `rv_hot_equip`（目前尚未設定 adapter）

## 目前狀態觀察
- 首次看到的「首頁畫面」實際上是啟動畫面（全螢幕 logo）。
- 真正功能主頁是 `MainActivity`，目前 UI 骨架已建立，但資料綁定與列表內容尚未填充。
