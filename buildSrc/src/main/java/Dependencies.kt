object Versions {
    val CIRCLE_IMAGR="3.1.0"
    val KOTLIN = "1.3.72"
    val COMPILE_SDK = 28
    val MIN_SDK_VERSION = 16
    val TARGET_SDK_VERSION = 28
    val VERSION_CODE = 1
    val VERSION_NAME = "1.0"
    val RXJAVA_VERSION = "2.2.13"
    val RX_ANDROID = "2.1.1"
    val GSON_VERSION = "2.8.7"
    val LIFE_CYCLE_VERSION = "2.2.0"
    val CONSTRAINT_LAYOUT = "2.0.4"
    val ANDROID_GRADLE_VERSION = "4.0.1"
    val APP_COMPAT_VERSION = "1.3.0"
    val CORE_KTX = "1.1.0"
    val ARCH_CORE_TESTING_VER = "2.0.0"
    val TEST_RUNNER_VER = "1.1.0"
    val RULES_VER = "1.1.0"
    val TRUTH_VER = "1.1.0"
    val JUNIT_EXT_VER = "1.1.0"
    val MATERIAL_VERSION = "1.2.1"
    val MOCKITO = "3.11.2"
    val MULTIDEX = "2.0.1"
    val JACOCO = "0.16.0"
    val HILT = "2.37"
    val FRAGMENT_KTX = "1.2.5"
    val RX_RETROFIT_ADAPTER = "2.6.1"
    val RETROFIT_VERSION = "2.7.1"
    val OKHTTP_LOGGING_INTERCEPTOR_VERSION = "3.12.1"
    val GSON_CONVERTER = "2.6.1"
    val ESPRESSO = "3.1.0"
    val GOOGLE_MAP = "17.0.0"
    val LOCATION_SERVICES = "15.0.1"
    val TIMBER  = "4.7.1"
    val PERMISSION_DISPATCHER = "4.8.0"
    val chuckerteam="3.4.0"
    val SDB_VERSION="1.0.6"
    val GLIDE = "4.11.0"
    val SPIN_KIT = "1.4.0"
    val rxbinding="2.0.0"
    val work_version="2.6.0"
}
object Coroutine_Version {
    const val coroutinesCoreVersion = "1.3.4"
    const val lifecycleExtensionsVersion = "2.2.0"
    const val lifecycleKTXVersion = "2.3.0-alpha04"
    const val coroutinesCommonVersion = "1.3.7"

}

object BuildPlugins {
    val androidGradle = "com.android.tools.build:gradle:${Versions.ANDROID_GRADLE_VERSION}"
    val kotlinGradlePlugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.KOTLIN}"
    val jacocoPlugin =  "com.vanniktech:gradle-android-junit-jacoco-plugin:${Versions.JACOCO}"
    val hiltPlugin = "com.google.dagger:hilt-android-gradle-plugin:${Versions.HILT}"
}

object Android {
    val minSDK = Versions.MIN_SDK_VERSION
    val targetSDK = Versions.TARGET_SDK_VERSION
    val versionCode = Versions.VERSION_CODE
    val versionName = Versions.VERSION_NAME
    val compileSDK = Versions.COMPILE_SDK
    val applicationId = "com.example.ace_store"
}


object Libs {

    val Coroutines = arrayOf(
        "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Coroutine_Version.coroutinesCoreVersion}",
        "org.jetbrains.kotlinx:kotlinx-coroutines-core-common:${Coroutine_Version.coroutinesCommonVersion}",
        "androidx.lifecycle:lifecycle-extensions:${Coroutine_Version.lifecycleExtensionsVersion}",
        "androidx.lifecycle:lifecycle-viewmodel-ktx:${Coroutine_Version.lifecycleKTXVersion}",
        "androidx.lifecycle:lifecycle-livedata-ktx:${Coroutine_Version.lifecycleKTXVersion}"
    )

    val kotlinStdLib = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:${Versions.KOTLIN}"
    val rxVersion = "io.reactivex.rxjava2:rxjava:${Versions.RXJAVA_VERSION}"
    val rxAndroid = "io.reactivex.rxjava2:rxandroid:${Versions.RX_ANDROID}"
    val material = "com.google.android.material:material:${Versions.MATERIAL_VERSION}"
    val gson = "com.google.code.gson:gson:${Versions.GSON_VERSION}"
    val constraintLayout = "androidx.constraintlayout:constraintlayout:${Versions.CONSTRAINT_LAYOUT}"
    val liveData = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.LIFE_CYCLE_VERSION}"
    val viewModel = "androidx.lifecycle:lifecycle-livedata-ktx:${Versions.LIFE_CYCLE_VERSION}"
    val appCompat = "androidx.appcompat:appcompat:${Versions.APP_COMPAT_VERSION}"
    val coreExt = "androidx.core:core-ktx:${Versions.CORE_KTX}"
    val multidex = "androidx.multidex:multidex:${Versions.MULTIDEX}"
    val hiltAndroid = "com.google.dagger:hilt-android:${Versions.HILT}"
    val hiltAndroidCompiler = "com.google.dagger:hilt-compiler:${Versions.HILT}"
    val fragmentKtx =  "androidx.fragment:fragment-ktx:${Versions.FRAGMENT_KTX}"
    val retrofit = "com.squareup.retrofit2:retrofit:${Versions.RETROFIT_VERSION}"
    val rxAdapter = "com.squareup.retrofit2:adapter-rxjava2:${Versions.RX_RETROFIT_ADAPTER}"
    val loggingInterceptor =
        "com.squareup.okhttp3:logging-interceptor:${Versions.OKHTTP_LOGGING_INTERCEPTOR_VERSION}"
    val gsonConverter = "com.squareup.retrofit2:converter-gson:${Versions.GSON_CONVERTER}"
    val googleMap = "com.google.android.gms:play-services-maps:${Versions.GOOGLE_MAP}"
    val locationServices = "com.google.android.gms:play-services-location:${Versions.LOCATION_SERVICES}"
    val timber =  "com.jakewharton.timber:timber:${Versions.TIMBER}"
    val permissionsDispatcher ="com.github.permissions-dispatcher:permissionsdispatcher:${Versions.PERMISSION_DISPATCHER}"
    val permissionsDispatcherProcessor ="com.github.permissions-dispatcher:permissionsdispatcher-processor:${Versions.PERMISSION_DISPATCHER}"
    val chuckerteamDebug ="com.github.chuckerteam.chucker:library:${Versions.chuckerteam}"
    val chuckerteamRelease="com.github.chuckerteam.chucker:library-no-op:${Versions.chuckerteam}"
    val glide = "com.github.bumptech.glide:glide:${Versions.GLIDE}"
    val sdp = "com.intuit.sdp:sdp-android:${Versions.SDB_VERSION}"
    @JvmStatic val LOADING = "com.github.ybq:Android-SpinKit:${Versions.SPIN_KIT}"
    val circleImageView="de.hdodenhof:circleimageview:${Versions.CIRCLE_IMAGR}"
    val rxBinding="com.jakewharton.rxbinding2:rxbinding:${Versions.rxbinding}"
    val worker_manger="androidx.work:work-runtime-ktx:${Versions.work_version}"
    val worker_manger_multi_process="androidx.work:work-multiprocess:${Versions.work_version}"
}

object TestLibs {
    val junit = "junit:junit:4.13"
    val espresso = "androidx.test.espresso:espresso-core:${Versions.ESPRESSO}"
    val archCoreTesting = "androidx.arch.core:core-testing:${Versions.ARCH_CORE_TESTING_VER}"
    val testRunner = "androidx.test:runner:${Versions.TEST_RUNNER_VER}"
    val rules = "androidx.test:rules:${Versions.RULES_VER}"
    val truth = "androidx.test.ext:truth:${Versions.TRUTH_VER}"
    val junitExt = "androidx.test.ext:junit:${Versions.JUNIT_EXT_VER}"
    val mockito = "org.mockito:mockito-core:${Versions.MOCKITO}"
    val mockitoAndroid = "org.mockito:mockito-android:${Versions.MOCKITO}"
    // For instrumentation tests
    val hiltAndroidTest =  "com.google.dagger:hilt-android-testing:${Versions.HILT}"
}