package csci318.demo.controller;

import csci318.demo.controller.dto.LibraryDTO;
import csci318.demo.model.Address;
import csci318.demo.model.Library;
import csci318.demo.service.LibraryService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class LibraryController {

    private final LibraryService libraryService;

    public LibraryController(LibraryService libraryService) {
        this.libraryService = libraryService;
    }

    @GetMapping("/libraries")
    List<LibraryDTO> all() {
        return libraryService.getAllLibraries()
                .stream()
                .map(library -> {
                    LibraryDTO libDTO = new LibraryDTO();
                    libDTO.setLibraryName(library.getName());
                    Address address =  library.getAddress();
                    libDTO.setPostcode(address == null ? "" : address.getLocation());
                    return libDTO;
                })
                .collect(Collectors.toList());
    }

    @GetMapping("/libraries/{id}")
    LibraryDTO findLibraryById(@PathVariable Long id) {
        LibraryDTO libDTO = new LibraryDTO();
        Library library = libraryService.getLibrary(id);
        Address address =  library.getAddress();
        libDTO.setPostcode(address == null ? "" : address.getLocation());
        libDTO.setLibraryName(library.getName());
        libDTO.setPostcode(library.getAddress().getLocation());
        return libDTO;
    }

    @PostMapping("/libraries")
    Library createLibrary(@RequestBody LibraryDTO newLibrary) {
        return libraryService.createLibrary(newLibrary);
    }

    @PutMapping("/libraries/{id}")
    Library updateLibraryName(@PathVariable Long id, @RequestBody LibraryDTO library) {
        return libraryService.updateLibraryName(id, library);
    }

    @PutMapping("/libraries/{id}/address/{addressId}")
    Library updateLibraryAddress(@PathVariable Long id, @PathVariable Long addressId) {
        return libraryService.updateLibraryAddress(id, addressId);
    }
}
