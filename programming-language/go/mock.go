package main

type Car struct {
    Name string
}

func (c Car) Run() { 
    fmt.Println("Real car " + c.Name + " is running")
}

type CarFactory struct {}

func (cf CarFactory) MakeCar(name string) Car {
    return Car{name}
}

type ICar interface {
    Run()
}

type ICarFactory interface {
    MakeCar(name string) ICar
}

func Transport(cf ICarFactory) {
    ...
    car := cf.MakeCar("lamborghini")
    car.Run()
    ...
}

type CarMock struct {
    Name string
}

func (cm CarMock) Run() {
    fmt.Println("Mocking car " + cm.Name + " is running")
}

type CarFactoryMock struct {}
func (cf CarFactoryMock) MakeCar(name string) ICar {
    return CarMock{name}
}

type CarFactoryWrapper struct {
    CarFactory
}

func (cf CarFactoryWrapper) MakeCar(name string) ICar {
    return cf.CarFactory.MakeCar(name)
}

// https://stackoverflow.com/questions/41053280/how-to-write-mock-for-structs-in-go