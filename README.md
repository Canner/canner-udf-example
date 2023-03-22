# canner-udf-example

canner-udf-example provides example for writing scalar UDF in Canner.
A scalar function is a function that returns one value per invocation; in most cases, you can think of this as returning one value per row

Example scalar functions in this project:

In MaskFunction.java

```text
ripemd_160(varchar):varchar
encrypt(varchar):varbinary
decrypt(varbinary):varchar
mask_column(varchar):varchar
mask_email(varchar,bigint):varchar
```

In MathFunction.java

```text
math_op(bigint,bigint):bigint
```

Note that `MaskFunction` and `MathFunction` are added in `UdfPlugin#getFunctions`. Doing this will make the udfs available in Canner.

### How to build and deploy udf to canner ?

- run `mvn clean package`
- in `{project-root}/target`, you will find `udf-1.0-SNAPSHOT-jar-with-dependencies.jar` please rename this file (replace `-` with `_`) and upload it to the User-Defined Function (
  UDF) page