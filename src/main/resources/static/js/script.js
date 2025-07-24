console.log("script loaded");

let currentTheme = getTheme();
setTheme(currentTheme); // Ensure the stored theme is applied on page load

function changeTheme() {
    // Apply the initial theme
    document.querySelector("html").classList.add(currentTheme);

    const changeThemeButton = document.querySelector("#theme_change");

    if (!changeThemeButton) {
        console.error("Button #theme_change not found!");
        return;
    }

    // Update icon text on load
    const icon = changeThemeButton.querySelector("i");
    if (icon) {
        icon.textContent = currentTheme === "light" ? " dark" : " light";
    }

    changeThemeButton.addEventListener("click", () => {
        console.log("Current theme:", currentTheme);
        console.log("Button clicked");

        const oldTheme = currentTheme;
        currentTheme = currentTheme === "light" ? "dark" : "light";

        setTheme(currentTheme);

        // Update the theme class
        document.querySelector("html").classList.remove(oldTheme);
        document.querySelector("html").classList.add(currentTheme);

        // Update icon text on theme change
        if (icon) {
            icon.textContent = currentTheme === "light" ? " dark" : "light";
        }
    });
}

// Store theme in localStorage
function setTheme(theme) {
    localStorage.setItem("theme", theme);
}

// Retrieve theme from localStorage
function getTheme() {
    let storedTheme = localStorage.getItem("theme");
    return storedTheme ? storedTheme : "light";
}

// Wait for DOM content to load before attaching event listener
document.addEventListener("DOMContentLoaded", changeTheme);
