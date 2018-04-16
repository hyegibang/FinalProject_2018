import win32gui
import time

def enum_window_titles():
    """
    Function that creates an enumeration object that contains the windows of the
    ui. Kind of obselete.
    """
    def callback(handle, data):
        titles.append(win32gui.GetWindowText(handle))

    titles = []
    win32gui.EnumWindows(callback, None)
    return titles
def print_windows():
    """
    Prints a list of every open window whose name is longer than 8 characters.
    """
    titles = enum_window_titles()
    for title in titles:
        if len(title) > 8:
            print(title)
    """
    for i in range(100):
        print( win32gui.GetWindowText(win32gui.GetForegroundWindow()))
        time.sleep(1)
    """

def get_current_window(file_types = ["SLDASM", "SLDPRT"]):
    window_type = "SOLIDWORKS Education Edition"
    current_window = win32gui.GetWindowText(win32gui.GetForegroundWindow())
    if window_type in (current_window):
        return True

        #TODO: Integrate part/assembly/drawing/sketch detection
        if current_window.split(".")[-1] == "SLDASM":
            return "Assembly"
    #    if current_window.split(".")[-1] ==
