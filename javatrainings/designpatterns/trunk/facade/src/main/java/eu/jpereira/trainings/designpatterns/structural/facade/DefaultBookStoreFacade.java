package eu.jpereira.trainings.designpatterns.structural.facade;

import eu.jpereira.trainings.designpatterns.structural.facade.model.Book;
import eu.jpereira.trainings.designpatterns.structural.facade.model.Order;
import eu.jpereira.trainings.designpatterns.structural.facade.service.BookDBService;
import eu.jpereira.trainings.designpatterns.structural.facade.service.CustomerDBService;
import eu.jpereira.trainings.designpatterns.structural.facade.service.OrderingService;
import eu.jpereira.trainings.designpatterns.structural.facade.service.WharehouseService;
import eu.jpereira.trainings.designpatterns.structural.facade.service.CustomerNotificationService;
import eu.jpereira.trainings.designpatterns.structural.facade.model.DispatchReceipt;
import eu.jpereira.trainings.designpatterns.structural.facade.model.Customer;
import eu.jpereira.trainings.designpatterns.structural.facade.model.Book;

public class DefaultBookStoreFacade implements BookstoreFacade {

    private String customerID;
    private String isbn;

    private WharehouseService wharehouseService;
    private OrderingService orderingService;
    private CustomerNotificationService customerNotificationService;
    private CustomerDBService customerService;
    private BookDBService bookService;

    @Override
    public void placeOrder(String customerId, String isbn) {
        this.customerID = customerId;
        this.isbn = isbn;
        Customer customer = this.customerService.findCustomerById(customerId);
        Book book = this.bookService.findBookByISBN(isbn);
        Order order = this.orderingService.createOrder(customer, book);
        DispatchReceipt dispatchReceipt = this.wharehouseService.dispatch(order);
        this.customerNotificationService.notifyClient(dispatchReceipt);
    }

    public void setWharehouseService(WharehouseService wharehouseService) {
        this.wharehouseService = wharehouseService;
    }

    public void setOrderingService(OrderingService orderingService) {
        this.orderingService = orderingService;
    }

    public void setCustomerNotificationService(CustomerNotificationService customerNotificationService) {
        this.customerNotificationService = customerNotificationService;
    }

    public void setCustomerService(CustomerDBService customerDBService) {
        this.customerService = customerDBService;
    }
    
    public void setBookService(BookDBService bookDBService) {
        this.bookService = bookDBService;
    }
    
}
