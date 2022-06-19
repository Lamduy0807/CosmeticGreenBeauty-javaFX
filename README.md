# GreenBeauty Cosmetic Management System.
## Table of contents
* [Thiết lập cơ sở dữ liệu](#Thiết-lập-cơ-sở-dữ-liệu)
* [Thiết lập chung](#Thiết-lập-chung)
* [Debug](#Debug)

## Thiết lập cơ sở dữ liệu
- Attack Database vào SQL Server thông qua file /src/Data/ComesticDB.mdf
- Chỉnh sửa thông tin về tên server, username, password trong file /src/Db/DbConnection.java
## Thiết lập chung
- Thêm 3 file .jar trong folder /src/lib/ vào thư viện
- Thêm dòng bên dưới vào VM Options
>--add-modules=javafx.controls,javafx.fxml --add-opens java.base/java.lang.reflect=ALL-UNNAMED
## Debug
- Dùng Netbean để mở sourcecode.
- Tiến hành chạy chương trình.
