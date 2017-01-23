# DividerLinearLayout
#前言
![分割线场景](http://upload-images.jianshu.io/upload_images/1743063-2bc0ccf44888a08b.PNG?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

在开发过程中，界面中条目间都需要我们设置分割线。大多时候，我们都通过如下布局代码片段设置：
```
    <View
        android:layout_width="match_parent"
        android:layout_height="0.5dp"
        android:background="#cccccc"/>
```
如上写法虽也方便，但会导致出现很多此类重复代码。另外，当我们需要隐藏其中某一条目之时，分割线同样也需做隐藏处理，较为繁琐。
因此，针对此类场景，我在LinearLayout可以设置分割线样式的属性的基础上，加以改进，提炼出使用场景更广的DividerLinearLayout。
#简介
项目地址：[DividerLinearLayout](https://github.com/kissonchan/DividerLinearLayout)
LinearLayout在Android3.0以上版本支持设置分割线样式的属性。关于该属性的具体使用方法，本文不在叙述。
DividerLinearLayout效果图如下：

![效果图](http://upload-images.jianshu.io/upload_images/1743063-23aeef2c14d255c5.jpg?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

有人可能会说，既然LinearLayout本身支持设置分割线，为啥你要多此一举。因为LinearLayout的分割线，仅支持四种模式。

SHOW_DIVIDER_NONE，无分割线。

SHOW_DIVIDER_BEGINNING，第一个可见child view前添加分割线。

SHOW_DIVIDER_MIDDLE，在两个可见View中间添加分割线。

SHOW_DIVIDER_END，最后一个可见child view前添加分割线。

LinearLayout的分割线无法同时设置以上所有模式。

另外LinearLayout的分割线是Drawable格式（比较重），大多情况下我们仅仅是要设置一条线而已。
DividerLayout支持的属性如下：

1.设置分割线颜色。

2.设置分割线的宽度。

3.设置头分割线是否可见。

4.设置尾分割线是否可见。

5.设置头分割线的padding。

6.设置尾分割线的padding。

7.支持代码动态设置以上所有属性。
