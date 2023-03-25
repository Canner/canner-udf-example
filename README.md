# canner-udf-example

canner-udf-example provides example for writing scalar UDF in Canner.
A scalar function is a function that returns one value per invocation; in most cases, you can think of this as returning one value per row

### Example scalar functions in this project

- In DataMasking.java

```text
mask_column(varchar):varchar
mask_email(varchar,bigint):varchar
```

- In EncryptDecryptWithKey.java

```text
encrypt(varchar):varbinary
decrypt(varbinary):varchar
```

In ExtendedHashFunction.java

```text
ripemd_160(varchar):varchar
```

- In MathOperation.java

```text
math_op(bigint,bigint):bigint
```

### Notices

- `resources/META-INF/services/io.trino.spi.Plugin` is needed for canner to load the plugin. And the content of this file is the full class name of the plugin class. (i.e.
  com.canner.udf.UdfPlugin in this example project)
- Note that all udf classes (i.e. DataMasking.class, EncryptDecryptWithKey, etc.) are added in `UdfPlugin#getFunctions`. Doing this will make the udfs available in Canner.

### How to build and deploy udf to canner ?

- JDK Version: 11+
- run `mvn clean package`
- in `{project-root}/target`, you will find `udf-1.0-SNAPSHOT-jar-with-dependencies.jar` please rename this file (replace `-` with `_`) and upload it to the User-Defined Function (
  UDF) page
