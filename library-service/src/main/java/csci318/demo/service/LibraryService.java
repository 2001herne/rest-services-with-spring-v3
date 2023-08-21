package csci318.demo.service;

import csci318.demo.controller.dto.LibraryDTO;
import csci318.demo.model.Address;
import csci318.demo.model.Library;
import csci318.demo.repository.AddressRepository;
import csci318.demo.repository.LibraryRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class LibraryService {


    private final LibraryRepository libraryRepository;
    private final AddressRepository addressRepository;
    private final RestTemplate restTemplate;

    public LibraryService(LibraryRepository libraryRepository, AddressRepository addressRepository, RestTemplate restTemplate) {
        this.libraryRepository = libraryRepository;
        this.addressRepository = addressRepository;
        this.restTemplate = restTemplate;
    }

    public List<Library> getAllLibraries(){
        return libraryRepository.findAll();
    }

    public Library getLibrary(long id) {
        return libraryRepository.findById(id).orElseThrow(RuntimeException::new);
    }

    public Library createLibrary(LibraryDTO newLibrary) {
        Library library = new Library();
        library.setName(newLibrary.getLibraryName());
        return libraryRepository.save(library);
    }

    public Library updateLibraryName(long id, LibraryDTO updatedLibrary) {
        Library library = libraryRepository.findById(id).orElseThrow(RuntimeException::new);
        library.setName(updatedLibrary.getLibraryName());
        return libraryRepository.save(library);
    }

    public Library updateLibraryAddress(long id, long addressId) {
        Library library = libraryRepository.findById(id).orElseThrow(RuntimeException::new);
        Address address = addressRepository.findById(addressId).orElseThrow(RuntimeException::new);
        library.setAddress(address);
        return libraryRepository.save(library);
    }
}
