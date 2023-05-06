# Tugas-Besar-IF2212-OOP-STI-2023
Tugas Besar IF2212 OOP STI 2023

## Members:
1. Vincent Franstyo 18221100
2. Rayhan Maheswara Pramanda 18221130
3. Rei Arriel Clyfton 18221084
4. Muhammad Thoriq Ramadhan 18221064

## How to build and run the project:
1. Open the terminal at the root folder, in this case in the `Tugas-Besar-IF2212-OOP-STI-2023` folder and type `./gradlew build`
2. Wait for the build process to finish
3. Notice that the build folder will be full of the class file
4. Direct yourself to the app folder with the comman `cd app`
5. To run the built project, type `java -cp build/libs/app.jar com.BNMO.App`
6. The App file will run in your terminal, congrats!

## How to play the game
1. Pada awal game, pemain akan diberi opsi untuk memulai memainkan game Sim-Plicity atau tidak. 
2. Setelah game dimulai, pemain akan diminta untuk membuat sebuah Sim dengan cara memasukkan nama Sim. Sim akan memiliki mood, kekenyangan, dan kesehatan di angka 80 dan memiliki uang awal berjumlah 100. 
3. Setelah Sim pertama dibuat, Sim-Plicity bisa mulai dimainkan di dunia berukuran 64x64. Pada halaman awal akan ditampilkan informasi tentang Sim yang sedang dimainkan, menu, dan aktivitas yang dapat dilakukan. 
4. 1 hari dalam Sim-Plicity sama dengan 12 menit di dunia nyata. Setiap harinya, Sim dapat melakukan berbagai aktivitas yang dapat mempengaruhi mood, kekenyangan, kesehatan, dan uang. 
5. Aksi yang bisa dilakukan oleh Sim dibedakan menjadi 4 jenis aksi, yaitu:
   - Aksi aktif → aksi-aksi yang memerlukan waktu dan keterlibatan Sim di dalamnya, contohnya kerja, olahraga, tidur, dan lain-lain 
   - Aksi non-aktif → aksi-aksi yang tidak memerlukan waktu, contohnya berpindah ruangan, melihat inventory, memasang barang, dan lain-lain 
   - Aksi upgrade → aksi-aksi untuk melakukan upgrade yang memerlukan uang dan waktu, tetapi waktunya berjalan beriringan dengan aksi aktif (ketika tidak ada aksi aktif, waktu tidak berjalan), contohnya beli barang dan upgrade rumah (menambah ruangan)
   - Aksi menambah Sim → aksi untuk membuat Sim baru yang hanya bisa dilakukan 1 hari sekali 
6. Di dalam game Sim-Plicity, tentu saja ada peraturan yang harus diikuti oleh para pemain, yaitu:Sim akan mati jika salah satu nilai dari mood, kekenyangan, atau kesehatan mencapai 0 
   - Setiap aksi aktif memiliki efek samping terhadap mood, kekenyangan, dan kesehatan 
   - Sim harus tidur minimal 3 menit setiap harinya, jika tidak dilakukan akan mengakibatkan efek samping pada kesehatan dan mood 
   - Sim harus buang air setidaknya 1 kali setiap habis makan, jika tidak dilakukan dalam 4 menit dari selesai makan akan mengakibatkan efek samping pada kesehatan dan mood 
   - Sim dapat bekerja maksimal 4 menit setiap harinya dan baru bisa berganti pekerjaan setelah bekerja 12 menit pada pekerjaan saat ini serta membayar ½ dari gaji pekerjaan baru
