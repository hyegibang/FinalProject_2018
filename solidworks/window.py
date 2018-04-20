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
def print_windows(filter = "SOLIDWORKS"):
    """
    Prints a list of every open window whose name is longer than 8 characters.
    """
    titles = enum_window_titles()
    for title in titles:
        if len(title) > 8 and filter in title:
            print(title)
    """
    for i in range(100):
        print( win32gui.GetWindowText(win32gui.GetForegroundWindow()))
        time.sleep(1)
    """

def get_current_window():
    file_types = ["SLDASM", "SLDPRT"]
    current_window = win32gui.GetWindowText(win32gui.GetForegroundWindow())
    if "SOLIDWORKS" in current_window:
        #TODO: More dynamic and flexible name finding
        name = current_window.split('[')[-1].split(']')[0].split('*')[0]
        for file_type in file_types:
            if file_type in current_window:
                return ((str(name), str(file_type)))
    return (("Invalid window type"))
if __name__ == '__main__':
    for i in range(10):
        a = get_current_window()
        print (a)
        print(type(a))
        time.sleep(1)
