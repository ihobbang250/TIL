import sys
import os
from PyQt5.QtWidgets import *
from PyQt5 import uic
import pykorbit

path = os.path.dirname(os.path.realpath(__file__))

form_class = uic.loadUiType(path+"\\"+"mywindow.ui")[0]

class MyWindow(QMainWindow, form_class):
    def __init__(self):
        super().__init__() # Need to initialize
        self.setupUi(self)
        self.pushButton.clicked.connect(self.inquiry)
        
    def inquiry(self):
        price = pykorbit.get_current_price("BTC")
        self.lineEdit.setText(str(price))

app = QApplication(sys.argv)
window = MyWindow()
window.show()
app.exec_()