# Wedraw
This is distributed Wedraw project. Originally, it is designed for [Distributed Systems subject](https://handbook.unimelb.edu.au/2017/subjects/comp90015) by **Quad** team:
- Yi Zhao
- Boheng Luan
- Maoyuan Xue
- and me.

We used **[JavaFx](https://docs.oracle.com/javafx/2/)** and **[JavaFX GraphicsContext](https://docs.oracle.com/javase/8/javafx/api/javafx/scene/canvas/GraphicsContext.html)** libraries. You can find the documentation on the **[wiki pages](https://github.com/bahriddin/wedraw/wiki)**.

You can generate server and client packages using src/Server/Network.java and src/GUI/run/run files' main methods, respectively (or use built jar files):

    # run server file first
    >_ java -jar server.jar [port_number]

    # next, run client file
    >_ java -jar client.jar

All the other part is in the GUI. Enjoy!

![Login Page](https://github.com/bahriddin/wedraw/blob/master/blob/img/login.png)

![Main Window](https://github.com/bahriddin/wedraw/blob/master/blob/img/wedraw.png)
