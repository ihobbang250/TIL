import sys
import os
from PyQt5.QtWidgets import *
from PyQt5 import uic
from PyQt5.QtCore import *
import pykorbit

path = os.path.dirname(os.path.realpath(__file__))

form_class = uic.loadUiType(path+"\\"+"mywindow.ui")[0]

class MyWindow(QMainWindow, form_class):
    def __init__(self):
        super().__init__() # Need to initialize
        self.setupUi(self)
        
        self.pushButton.clicked.connect(self.inquiry) # Event by push button
        
        self.timer = QTimer(self) # Set timer
        self.timer.start(1000) # Event per second
        self.timer.timeout.connect(self.inquiry) # Event by timer
        
    def inquiry(self):
        cur_time = QTime.currentTime()
        str_time = cur_time.toString("hh:mm:ss")
        self.statusBar().showMessage(str_time)
        price = pykorbit.get_current_price("BTC")
        self.lineEdit.setText(str(price))

app = QApplication(sys.argv)
window = MyWindow()
window.show()
app.exec_()