import tornado.ioloop
import tornado.web
import tornado.websocket
import sys
import json
class IndexHandler(tornado.web.RequestHandler):
 def get(self):
  self.render("""index.html""");
class JsonHandler(tornado.web.RequestHandler):
 def get(self):
  self.render('home.json'); 
class SimpleWebSocket(tornado.websocket.WebSocketHandler):
 dict=dict([])
 connections = set()
 def check_origin(self, origin):
    return True
 def open(self):
  self.connections.add(self)
  print('Connected');
 def on_message(self, message): 
  f=open("home.json",'w')
  f.write(message)
  f.close()
 def on_close(self):
  self.connections.remove(self)
  print('Disconnected');
def make_app():
 return tornado.web.Application([
 (r"/websocket", SimpleWebSocket),
 (r"/",IndexHandler),
 (r"/requestJson",JsonHandler)
 ])
if __name__ == "__main__":
 try:
  app = make_app()
  print('Server Instantiated,Listening On Port 8888 ....');
  app.listen(8888)
  tornado.ioloop.IOLoop.current().start()
 except KeyboardInterrupt as ke:
  sys.exit()
