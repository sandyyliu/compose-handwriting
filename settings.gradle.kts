pluginManagement {
    repositories {
        maven("https://mirrors.cloud.tencent.com/nexus/repository/maven-public/")
        maven("http://maven-ghy.byd.com:8081/repository/maven-public/") {
            isAllowInsecureProtocol = true
            credentials {
                username = "dilinkreader"
                password = "dilinkreader"
            }
        }
        maven("https://maven-ghy.byd.com/repository/maven-public/") {
            isAllowInsecureProtocol = true
            credentials {
                username = "dilinkreader"
                password = "dilinkreader"
            }
        }
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        maven("https://mirrors.cloud.tencent.com/nexus/repository/maven-public/")
        maven("http://maven-ghy.byd.com:8081/repository/maven-public/") {
            isAllowInsecureProtocol = true
            credentials {
                username = "dilinkreader"
                password = "dilinkreader"
            }
        }
        maven("https://maven-ghy.byd.com/repository/maven-public/") {
            isAllowInsecureProtocol = true
            credentials {
                username = "dilinkreader"
                password = "dilinkreader"
            }
        }
    }
}
rootProject.name = "handwriting"
include(":app")
 