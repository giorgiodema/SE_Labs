# Notes
From 2020 some java dependencies requires https connection, for this reason old versions of Maven could resolve into errors while downloading the dependencies.
To fix this either update Maven or switch to hppts://... the address of the Maven Central repository.
 ```xml
<repositories>
    <repository>
        <id>Maven Central</id>
        <url>https://repo.maven.apache.org/maven2/</url>
    </repository>
</repositories>
  ```
