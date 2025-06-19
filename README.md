这个是各种 ai 生成代码测试，看看适合胜任那些工作

[systemsettinggpt](systemsettinggpt)
GPT 做的，修改国际化和修改深色浅色主题

[systemsettinglingma](systemsettinglingma)
lingma 做的，修改国际化和修改深色浅色主题


[systemsettingtrae](systemsettingtrae)
trae 做的，修改国际化和修改深色浅色主题


[zt_lanhu](zt_lanhu)
更加蓝湖提供图片，编写布局文件

[zt_task_act_and_frag](zt_task_act_and_frag)[zt_taskv1](zt_taskv1)[zt_taskv2](zt_taskv2)
 这三个都是让 ai 给我实现的这个功能 ，实现的都不好

需求：实现 Activity 与 Fragment 的混合导航堆栈管理

基础结构：
存在一个主 Activity (A)。

Activity A 底部有三个 Tab 分组，分别承载以下 Fragment：

发现 Fragment (a)

交易 Fragment (b)

我的 Fragment (c)

场景一：从 Fragment 启动 Activity 并返回

当用户位于 发现 Fragment (a) 时，可以启动一个新的 Activity (B)。

用户在 Activity B 中点击系统返回键或应用内返回按钮时，必须能正确返回到主 Activity A 并显示 发现 Fragment (a) 的状态。

场景二：跨 Fragment 启动 Activity 并嵌套跳转回 Fragment

当用户位于 交易 Fragment (b) 时，可以启动一个新的 Activity (C)。

在 Activity (C) 中，提供一个按钮。点击此按钮应能直接导航并显示主 Activity A 中的 我的 Fragment (c)。

当用户此时位于 我的 Fragment (c) 时，点击系统返回键或应用内返回按钮时，必须能正确返回到 Activity (C) 的状态（而不是直接回到主 Activity A 的初始状态或交易 Fragment (b)）。

当用户返回到 Activity (C) 后，在 Activity C 中点击系统返回键或应用内返回按钮时，必须能正确返回到主 Activity A 并显示 交易 Fragment (b) 的状态。