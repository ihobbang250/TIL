import sys
import os
from PyQt5.QtWidgets import *
from PyQt5.QtGui import *

path = os.path.dirname(os.path.realpath(__file__))

class MyWindow(QMainWindow):
    def __init__(self):
        super().__init__() # Need to initialize
        self.setGeometry(100, 100, 500, 500) # Screening location x,y and Size x, y
        self.setWindowTitle("Test") # Setting window title
        self.setWindowIcon(QIcon(path+ "\\"+ "icon.png")) # Setting window icon
        
        btn1 = QPushButton("Button1", self) 
        btn1.move(10, 10)
        btn1.clicked.connect(self.btn_cliked)
        
        btn2 = QPushButton("Button2", self)
        btn2.move(10, 40)
        
    def btn_cliked(self):
        print("clicked button")
        

app = QApplication(sys.argv)
window = MyWindow()
window.show()
app.exec_()