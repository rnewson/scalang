package scalang

class MainService(ctx : ServiceContext[NoArgs]) extends Service(ctx) {

  override def handleCall(tag : (Pid, Reference), msg : Any) : Any = {
    sendAfter(self, 'exit, 0)
    'ok
  }

  override def handleInfo(msg : Any) {
    System.exit(0)
  }

}

object Main extends App {

  Thread.setDefaultUncaughtExceptionHandler(
    new Thread.UncaughtExceptionHandler {
      def uncaughtException(t : Thread, e : Throwable) {
        System.exit(1)
      }
    }
  )

  val node = Node("scalang@127.0.0.1", "monster")
  node.spawnService[MainService, NoArgs]('main, NoArgs)
  Thread.sleep(15*1000)
  System.exit(1)
}
