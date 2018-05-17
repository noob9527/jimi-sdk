# jimi sdk

### installation
```
repositories {
    mavenCentral()
    maven { url 'https://jitpack.io' }
}

dependencies {
    compile("com.github.noob9527:jimi-sdk:master-SNAPSHOT")
    // ...
}
```
### getting started
if you are using spring boot, related service are autoconfigure, otherwise you have to create them on your own

### configuration
| key | required | default |
| - | - | - |
| jimi.app-key          | true ||
| jimi.app-secret       | true ||
| jimi.user-id          | true ||
| jimi.user-password    | true ||
| jimi.api-version      | true | 0.9(1.x is not support yet) |
| jimi.api-url          | true | http://open.aichezaixian.com/route/rest/ |
| jimi.log-level        | true | NONE |
