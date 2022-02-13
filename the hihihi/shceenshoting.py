#modified sheenshot thinging

import wx

#filePathz = "F:\screenshotinz.png"

wx.App()  # Need to create an App instance before doing anything
filePathz = "F:\screenshotinz.png"

screen = wx.ScreenDC()
size = screen.GetSize()
bmp = wx.EmptyBitmap(size[0], size[1])
mem = wx.MemoryDC(bmp)
mem.Blit(0, 0, size[0], size[1], screen, 0, 0)
del mem  # Release bitmap
bmp.SaveFile(filePath , wx.BITMAP_TYPE_PNG)
