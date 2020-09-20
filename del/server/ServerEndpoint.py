import tornado.ioloop
import tornado.web
import tornado.websocket
import sys
import json

class HomeDTO(object):
 def __init__(self):
  self.rooms=[];

class RoomDTO(object):
 def __init__(self):
  self.name="";
  self.id=0;
  self.boards=[];

class BoardDTO(object):
 def __init__(self):
  self.roomName="";
  self.roomId=0;
  self.name="";
  self.id=0;
  self.status=False;
  self.components=[];

class ComponentDTO(object):
 def __init__(self):
  self.roomName="";
  self.roomId=0;
  self.boardName="";
  self.boardId=None;
  self.name="";
  self.id=0;

def updateJson(cJson):
 roomId=cJson["roomId"]
 roomName=cJson["roomName"]
 boardId=cJson["boardId"]
 boardName=cJson["boardName"]
 componentId=cJson["componentId"]
 componentName=cJson["componentName"]
 componentStatus=cJson["componentStatus"]
 for room in h.rooms:
  if room.id==roomId:
   for board in room.boards:
    if board.id==boardId:
     for component in board.components:
      if component.id ==componentId:
       component.status=componentStatus
       reformJson()

def updateBoardJson(bJson):
 roomId=bJson["roomId"]
 roomName=bJson["roomName"]
 boardId=bJson["boardId"]
 boardName=bJson["boardName"]
 boardStatus=bJson["boardStatus"]
 for room in h.rooms:
  if room.id==roomId:
   for board in room.boards:
    if board.id==boardId:
     board.status=boardStatus
     reformJson()



def reformJson():
  global newJson;
  newJson=json.dumps(h,default=objectToJson);

def objectToJson(obj):
 return obj.__dict__;

def getNewJson():
 return newJson;


def populateDataStructure():
 global r;
 global b;
 global c;
 global h;
 js= open('home.json');
 pd=json.load(js);
 for home,rooms in pd.items():
  h=HomeDTO();
  for room in rooms:
   r=RoomDTO();
   for v,k in room.items():
    if(isinstance(k,list)):
     for board in k:
      b=BoardDTO();
      for i,j in board.items():
       if(isinstance(j,list)):
        for component in j:
         c=ComponentDTO();
         for x,y in component.items():
          if x=="id":
           c.id=y;
          if x=="name":
           c.name=y;
          if x=="status":
           c.status=y;
          if x=="minLevel":
           c.minLevel=y;
          if x=="maxLevel":
           c.maxLevel=y;
          if x=="regulatable":
           c.regulatable=y;
          if x=="roomName":
           c.roomName=y;
          if x=="roomId":
           c.roomId=y;
          if x=="boardName":
           c.boardName=y;
          if x=="boardId":
           c.boardId=y;
         b.components.append(c);
       else:
         if i=="id":
          b.id=j;
         if i=="name":
          b.name=j;
         if i=="roomName":
          b.roomName=j;
         if i=="roomId":
          b.roomId=j;
      r.boards.append(b);
    else:
     if v=='id':
      r.id=k;
     if v=='name':
      r.name=k;
   h.rooms.append(r);

class IndexHandler(tornado.web.RequestHandler):
 def get(self):
  self.render('index.html');

class IndexTesting(tornado.web.RequestHandler):
 def get(self):
  self.render('index1.html');

class JsonHandler(tornado.web.RequestHandler):
 def post(self):
  self.render("home.json");

class NewJsonHandler(tornado.web.RequestHandler):
 def post(self):
  self.write(getNewJson());
  self.flush();

class OnStatusChanged(tornado.web.RequestHandler):
 def post(self):
  bytesJson=self.request.body;
  jsonString=bytesJson.decode().replace("'",'"');
  print(str(jsonString));
  tempJson=json.loads(jsonString);
  cJson=dict({});
  cJson["roomId"]=tempJson["roomId"]
  cJson["roomName"]=tempJson["roomName"]
  cJson["boardId"]=tempJson["boardId"]
  cJson["boardName"]=tempJson["boardName"]
  cJson["componentId"]=tempJson["id"]
  cJson["componentName"]=tempJson["name"]
  cJson["componentStatus"]=tempJson["status"]
  updateJson(cJson);
  self.write(getNewJson());
  self.flush();  
  SimpleWebSocket.on_status_change(cJson);

class OnBoardStatusChanged(tornado.web.RequestHandler):
 def post(self):
  bytesJson=self.request.body;
  jsonString=bytesJson.decode().replace("'",'"');
  tempJson=json.loads(jsonString);
  bJson=dict({});
  bJson["roomId"]=tempJson["roomId"]
  bJson["roomName"]=tempJson["roomName"]
  bJson["boardId"]=tempJson["id"]
  bJson["boardName"]=tempJson["name"]
  bJson["boardStatus"]=tempJson["status"]
  print(bJson);
  updateBoardJson(bJson);
  self.write(getNewJson());
  self.flush();  
  SimpleWebSocket.on_status_change(bJson);
 

class SimpleWebSocket(tornado.websocket.WebSocketHandler):
 dict=dict([]);
 connections = set();
 def check_origin(self, origin):
    return True;
 def open(self):
  self.connections.add(self);
  print('Connected');
 def on_message(self, message):
  print(message);
  cJson=json.loads(message);
  updateJson(cJson);
 def on_close(self):
  self.connections.remove(self);
  print('Disconnected');
 @classmethod
 def on_status_change(self, cJson):
  for c in  self.connections :
   c.write_message(cJson);


def make_app():
 return tornado.web.Application([
 (r"/websocket",SimpleWebSocket),
 (r"/",IndexHandler),
 (r"/test",IndexTesting),
 (r"/requestNewJson",NewJsonHandler),
 (r"/requestJson",JsonHandler),
 (r"/onStatusChanged",OnStatusChanged),
 (r"/onBoardStatusChanged",OnBoardStatusChanged)
 ]);

if __name__ == "__main__":
 try:
  populateDataStructure();
  reformJson();
  app = make_app();
  print('Server Instantiated,Listening On Port 8888 ....');
  app.listen(8888);
  tornado.ioloop.IOLoop.current().start();
 except KeyboardInterrupt as ke:
  sys.exit();