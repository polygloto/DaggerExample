# This application is made for learning Dagger 2.

## Первичная настройка
1. Созданы классы Car, Engine, Fuel
2. Перед конструкторами классов Car, Engine и Fuel добавлены аннотации **@Inject**, тем сам даю понять dagger, что эти классы должны быть внедрены при необходимости.
3. Dagger должен знать как создавать все объекты, которые он должен внедрять. 
  Для того чтобы перечислить все классы, которые нужно внедрить (Inject) используется аннотация **@Component**, которая объявляется для интерфейса (DaggerComponent).
4. Собрать проект (Build — Rebuild project). После этого dagger сгенерирует необходимые классы и фабрику для инициализации компонентов. 
  Название фабрики будет совпадать с названием интерфейса, в котором инициализировались классы для даггер (getCar, getEngine ..), 
  за исключением того, что будет добавлен префикс "Dagger", т.е. на выходе получится класс DaggerDaggerComponent.
5. Готово. Можно создать поле car типа Car в MainActivity

## Внедрение методов и полей
Dagger может внедрять зависимости для полей и методов. Но эти внедрения следует использовать при крайней необходимости.
Пример внедрения метода (set) находится в классе Car.
1. В интерфейс DaggerComponent добавить метод внедрения для MainActivity - fun inject(activity: MainActivity)
2. Определить поля, которые должны быть внедрены в MainActivity. Внедряемые поля должны быть lateinit var и видны всем (public)
3. Вызвать метод inject() для внедрения полей активити - DaggerDaggerComponent.create().inject(this)

## Отложенная инициализация в dagger
При запуске приложения не всегда нужны экземпляры всех классов. 
В dagger есть 2 вида инициализации внедряемых объектов: Provider<> И Lazy<>

**Provider** – инициализация происходит при первом обращении к объекту и при каждом вызове будет возвращен новый экземпляр объекта.
  Пример использования Provider в классе Car
  При каждом вызове метода get() получаем новый экземпляр нужного объекта.
  
**Lazy** – инициализация происходит при первом обращении, далее возвращается ранее кешированный экземпляры
  Пример использования Lazy в классе Engine
  При каждом вызове метода get() получаем один и тот же экземпляр.


## Модули dagger
Бывают случаи, когда dagger не понимает намерений. Например, у класса Car есть поле типа Driver, который наследуется классом Mikhailov.
При попытке внедрить поле с типом интерфейс - будет ошибка «cannot be provided without an @Provides-annotated methods».
Для решения этой проблемы dagger предлагает использовать Модули. 
Модули обеспечивают dagger дополнительной информацией, которую он не может получить самостоятельно. 
В качестве модуля можно использовать интерфейсы или объекты (object).

Для решения создан модуль DaggerModul.
В методе bindDriver нужно объяснить dagger, как необходимо инициализировать интерфейс.
Так же в компоненте нужно перечислить все существующие модули dagger - @Component(modules = [DaggerModule::class], ...)

Аннотация **@Provides** описывает те случаи, когда нужно явно описать - экземпляр какого класса нужно инициализировать. 
Пример использования в объекте DaggerModuleObject. 
Тем самым я говорю dagger, что при инициализации поля cylinder нужен экземпляр класса BoschCylinder.

Аннотация **@Named**. Несколько экземпляров одного типа
При возникновении ошибки «(class name) is bound multiple times» воспользоваться аннотацией @Named.

В объекте DaggerModuleObject: метод getColor() по умолчанию, без него dagger будет ругаться об отсутствии класса:
«cannot be provided without an Inject constructor or an Provides-annotated method».

Методы getColorBlue() и getColorRed() возвращают экземпляры одного и того же класса.

В классе Car: пометить нужные поля аннотацией @Named
